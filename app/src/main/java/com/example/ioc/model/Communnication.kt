package com.example.ioc.model

import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashSet

class Communnication {
    var json: Pojo
    var position:Int = 0;
    lateinit var key:String

    init {
//        key = "MODECODEX"
        key = "S2X"
//        var modelX = initSat5Component()

        json = Pojo.NOELEMENT
//        json.addModel(modelX,0)
//        modelX.addModel(modelX.get("S2XE"), json.component.indexOf(modelX)+1)
    }


    fun upModel() : Map<String, Pojo>{
        if (json.component.size==0)
            return json.model
        if (position==0)
            return json.get(position).model
        val component = json.get(position)
        val model = json.get(--position).model
        this.key = model.keys.firstOrNull() ?: ""
        return model//.mapValues { entry -> entry.value.toString() }
    }

    fun downModel(it: Pojo?): Map<String, Pojo> {
        val component = json.get(position)
        if (//!component.get(key).model.isEmpty() ||
            (it != null && it.existModel()) ) {
            json.addModel(it, position+1) // add rigth or to end
            component.model.put(key, Pojo.embend(it)) //delete or scroll&create
        }
        if (position==json.size() -1)
            return component.model// .mapValues { entry -> entry.value.toString() }
        val model = json.get(++position).model
        this.key = model.keys.firstOrNull() ?: ""
        return model//.mapValues { entry -> entry.value.toString() }
    }

    fun crudValue(key: String, crud: Pojo)  {
        val component = json.get(position)
        if (crud.modelName.isBlank()) {
            component.remove(key)
            this.key = component.model.keys.firstOrNull() ?: ""
            return
        }
        if (key.isBlank()) {
            component.remove(this.key)
            this.key = component.model.keys.firstOrNull() ?: ""
            return
        }
        component.model.put(key, crud)

//        if (model.keys.isEmpty())
//            return mapOf()
//        return mapOf( Pair(model.keys.last()
//                , model.values.last()))
    }

    fun initKey(key: String) {
        this.key = key;
    }

    fun prevPair(key : String) : JsonPair<String, String> {
        val model = json.get(position).model
        val keys = model.keys.toList()
        val mPos = keys.indexOf(key)
        if (mPos==0)
            return JsonPair(key, model.get(key)!!.toString())
        if (mPos==-1) {
            initKey(model.keys.firstOrNull()?:"null")
            return JsonPair(this.key, model.get(this.key)!!.toString())
        }
        initKey(keys.get(mPos-1))
        return JsonPair(this.key, model.get(this.key)!!.toString())
    }

    fun nextPair(key : String) : JsonPair<String, String> {
        val model = json.get(position).model
        val keys = model.keys.toList()
        val mPos = keys.indexOf(key)
        if (mPos==keys.size-1)
            return JsonPair(key, model.get(key)!!.toString())
        if (mPos==-1) {
            initKey(model.keys.firstOrNull()?:"null")
            return JsonPair(this.key, model.get(this.key)!!.toString())
        }
        initKey(keys.get(mPos+1))
        return JsonPair(this.key, model.get(this.key)!!.toString())
    }
    enum class COMP (val key:Int) {
        FMT4(1), FMT5(2), FPGA(4), NOTHING(0)
    }

    fun syncWithComponent(comp:COMP) : Map<String,Pojo> {
        if (comp == COMP.NOTHING)
            return JsonMap()
        json.component.clear()
        var model: Pojo = json //fake
        var key1 = "S2XE"
        if (comp == COMP.FMT4){
            key = "SATELLITE4"
            model = initSat4Component()
        }else if (comp == COMP.FMT5) {
            key = "SATELLITE5"
            model = initSat5Component()
        }else if (comp == COMP.FPGA) {
            key = "FPGA"
            model = initSatS2andS2XComponent()
            key1 = "S2X"
        }
        key = key1 // remove null field
        json.addModel(model,0)
        model.addModel(model.get(key1), json.component.indexOf(model)+1)
        return json.model
    }

    private fun initSat5Component(): Pojo{
        var modelX = Pojo(key,Pojo.COMPONENT, JsonMap())
        modelX.model.put("MODECODEX",Pojo.build("57"))
        modelX.model.put("TYPE",Pojo.build("0"))
        modelX.model.put("SIZE",Pojo.build("86400"))

//        json.addModel(Pojo("S2X",Pojo.COMPONENT, mutableMapOf()),1)
        modelX.model.put("S2XE",Pojo.build(""))
        val modelXE = modelX.get("S2XE")//.put("S2XE","").get("S2XE")
        modelXE.model.put("SPREAD",Pojo.build("5"))
        modelXE.model.put("PLI", Pojo.build("1"))
        modelXE.model.put("SFL", Pojo.build("612540"))
        modelXE.model.put("FMT", Pojo.build("5"))
        position = 0
        return modelX
    }
//
    private fun initSat4Component(): Pojo{
        var modelX = Pojo(key,Pojo.COMPONENT, JsonMap())
        modelX.model.put("SIZE",Pojo.build("12960"))
        modelX.model.put("MODECODEX",Pojo.build("23"))
        modelX.model.put("TYPE",Pojo.build("0"))

        modelX.model.put("S2XE",Pojo.build(""))
        val modelXE = modelX.get("S2XE")//.put("S2XE","").get("S2XE")
        modelXE.model.put("SPREAD",Pojo.build("1"))
        modelXE.model.put("PLI", Pojo.build("1"))
        modelXE.model.put("SFL", Pojo.build("612540"))
        modelXE.model.put("FMT", Pojo.build("4"))
        position = 0
        return modelX
    }

    private fun initSatS2andS2XComponent(): Pojo{
        var model = Pojo(key,Pojo.COMPONENT, JsonMap())
        model.model.put("SIZE",Pojo.build("16200"))
        model.model.put("MODECODE",Pojo.build("3"))
        model.model.put("TYPE",Pojo.build("2"))

        model.model.put("S2X",Pojo.build(""))
        var modelX = model.get("S2X")
        modelX.model.put("SIZE",Pojo.build("12960"))
        modelX.model.put("MODECODEX",Pojo.build("23"))
        modelX.model.put("TYPE",Pojo.build("0"))
        position = 0
        return model
    }

}

class JsonMap<T, U> : AbstractMutableMap<T, U>() {
    val map:MutableMap<T,U> = mutableMapOf()
    val _entries = map.entries

    override val entries: MutableSet<MutableMap.MutableEntry<T, U>>
        get() = _entries
    override fun put(key: T, value: U): U? = map.put(key, value)

    override fun toString(): String {
        return entries
            .map { entry -> JsonPair(entry.key, entry.value).toString() }
            .toString()
            .replace(" ","")
            .replace(",",":")
            .replace(";\":",",")
            .replace(":","\":")
            .replace("[","{")
            .replace("]","}")
            .replace(";\"}","}")
    }
}

class Pojo (val modelName:String
, var component:LinkedList<Pojo>
, var model: JsonMap<String, Pojo>) {
    constructor(modelName:String, value:String) : this(modelName, COMPONENT, JsonMap()) {
        if (!modelName.isBlank())
            put(modelName, value)
    }

    companion object {
        val heap = LinkedList<Pojo>()
        val COMPONENT = LinkedList<Pojo>()
            val NOELEMENT:Pojo = Pojo("NoElement", COMPONENT , JsonMap())

        fun build(value:String?) :Pojo {
            val _value = value?:"null"
            if (_value.equals(null))
                return NOELEMENT
            return Pojo(_value, _value)
        }

        fun embend(it: Pojo): Pojo {
            if (it.model.containsKey(it.modelName))
                return it
            val values = it.get(it.modelName).toString()
            if (!values.contains("{"))
                return it
            val its = values.replaceFirst("{","").replace("}","") //TODO
                .split(",").iterator()
            while (its.hasNext()) {
                val v = its.next()
                val vs = v.split(":")
                if (!v.equals(it.modelName))
                    it.model.put(vs[0], build(vs[1]))
            }
            return it
        }
    }
    init {
        heap.add(this)
    }
    override fun toString(): String {
        if (model.containsKey(modelName))
            return StringBuilder().append(modelName).toString()
//                .append("\":\"").append(model.get(modelName)!!.modelName).append("\"").toString()
        val items = model.map { entry ->
            Pair(entry.key, entry.value) }
        return items.toString()
    }

    private fun put(key:String, value:String): Pojo {
        val model = Pojo(value, heap, JsonMap())
//        val isInited = key.equals(modelName) || (!this.model.isEmpty())
//        if (isInited) {
//            this.model.put(key, model)
//        } else {
//            val pojo = Pojo(key, heap, mutableMapOf(Pair(key,this)))
//            pojo.model.put(key, model)
//            this.model.put(key, pojo)
//        }
        this.model.put(key, model)
        return this
    }

    fun get(key:String): Pojo {
        if (key.equals(modelName))
            return this
        return model.get(key)?:NOELEMENT
    }

    fun remove(key:String) {
        heap.remove(model.remove(key))
    }

    fun addModel(model: Pojo, pos:Int) {
//        if (!model.modelName.isBlank())
            component.add(pos, model)
    }
    fun addSubcomponent(subcomponent: LinkedList<Pojo>) {
        component = subcomponent
    }

    fun remove(model:Pojo) {
        component.remove(model)
        heap.remove(model)
    }

    fun get(pos:Int) = component.get(pos)
    fun size() = component.size
    fun existModel(): Boolean {
        if (model.size>1)
            return true
        return !(model.get(modelName)?.model?.isEmpty()?:true)
    }
}

data class JsonPair<out K, out T>(val first: K, val second: T) {
    val pair:Pair<K,T>
    init {
        pair = Pair(first, second)
    }

    override fun toString(): String {
        return pair.toString()
//            .replace(",",":")
            .replace("(","\"")
            .replace(")",";\"")
    }
}
