#' @export
create_user <- function(url=NULL) {
  
  if(file.exists('.mogger.config')) {
    line = get_config()
    user_name = line[1]
    password = line[2]
    url = line[3]
  } else {
  
    user_name = readline('user: ')
    password = getPass::getPass('password: ')
  }
  
  password = digest::digest(password, 'md5')
  
  url = paste0(url, "mogger/api/v1/user")
  
  body = list("userName"=user_name,
              "password"=password)
  
  # uploading data
  r = httr::content(httr::POST(url=url,
                               body=body,
                               encode='json'),
                    'text')
  
  # return
  r
}

