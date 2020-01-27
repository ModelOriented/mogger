#' @export
create_config = function(url) {
  user_name = readline('user: ')
  password = getPass::getPass('password: ')
  
  write(user_name, '.mogger.config')
  write(password, '.mogger.config', append = TRUE)
  write(url, '.mogger.config', append = TRUE)
}
