#' @export
upload_audit <- function(model_id,
                         dataset_id,
                         measure,
                         value,
                         url=NULL) {
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
  
  stopifnot(class(user_name) == 'character')
  stopifnot(class(password) == 'character')
  stopifnot(class(measure) == 'character')
  stopifnot(class(url) == 'character')
  
  url = paste0(url, "mogger/api/v1/audit")
  
  body = list(
    "modelId"=model_id,
    "datasetId"=dataset_id,
    "measure"=measure,
    "value"=value
  )
  
  # posting
  r = httr::POST(url=url,
                 config=add_headers(userName=user_name, password=password),
                 body=body,
                 encode='json')
  
  # return
  httr::content(r)
}
