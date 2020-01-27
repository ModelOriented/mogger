#' @export
get_config = function() {
  readLines('.mogger.config')
}
