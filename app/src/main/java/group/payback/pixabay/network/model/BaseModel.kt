package group.payback.pixabay.network.model

data class BaseModel(
                       val query : String = "",
                       val pageSize: Int=20,
                       val pageNum: Int=1)