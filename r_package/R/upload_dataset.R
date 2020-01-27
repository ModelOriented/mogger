#' @export
upload_dataset <- function(dataset_name,
                           task,
                           dataset_desc,
                           dataset_url,
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
  
  stopifnot(class(user_name)=='character')
  stopifnot(class(password)=='character')
  stopifnot(class(dataset_name)=='character')
  stopifnot(class(dataset_desc)=='character')
  
  url = paste0(url, "mogger/api/v1/dataset")
  
  body = list("datasetName"=dataset_name,
              "userName"=user_name,
              "taskName"=task,
              "datasetDescription"=dataset_desc,
              "url"=dataset_url)
  
  # uploading data
  r = httr::content(httr::POST(
    url=url,
    config=add_headers("userName"=user_name, "password"=password),
    body=body,
    encode='json'), 
    'text')
  
  # return
  r
}

