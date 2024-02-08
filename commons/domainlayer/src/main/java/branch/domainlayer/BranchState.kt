package branch.domainlayer

sealed interface BranchState<out T> {

    data object Loading : BranchState<Nothing>

    data class Success<T>(val data: T) : BranchState<T>

    data class Error(val error: Throwable) : BranchState<Nothing>

}