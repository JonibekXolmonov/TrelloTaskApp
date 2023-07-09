package uz.realsoft.task.common

sealed class UiStateList<out T> {
    data class SUCCESS<out T>(val data: List<T>, var type: String = "") : UiStateList<T>()
    data class ERROR(val message: String, var fromServer: Boolean = false) : UiStateList<Nothing>()
    object LOADING : UiStateList<Nothing>()
    object EMPTY : UiStateList<Nothing>()
}

sealed class UiStateObject<out T> {
    data class SUCCESS<out T>(val data: T) : UiStateObject<T>()
    data class ERROR(val message: String, var fromServer: Boolean = false) :
        UiStateObject<Nothing>()

    object LOADING : UiStateObject<Nothing>()
    object EMPTY : UiStateObject<Nothing>()
}

fun provideImage() = listOf(
    "https://images.unsplash.com/photo-1497215728101-856f4ea42174?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c21hcnQlMjBvZmZpY2V8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=400&q=60",
    "https://images.unsplash.com/photo-1604328702728-d26d2062c20b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8c21hcnQlMjBvZmZpY2V8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=400&q=60",
    "https://images.unsplash.com/photo-1604328702728-d26d2062c20b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8c21hcnQlMjBvZmZpY2V8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=400&q=60",
    "https://images.unsplash.com/photo-1599580546666-c26f15e00933?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fHNtYXJ0JTIwb2ZmaWNlfGVufDB8fDB8fHww&auto=format&fit=crop&w=400&q=60",
)