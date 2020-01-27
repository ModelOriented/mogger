#' @export
upload_model <- function(model,
                         dataset_id,
                         model_name,
                         target,
                         model_desc,
                         tags,
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
  
  # checking input
  stopifnot(class(user_name) == 'character')
  stopifnot(class(password) == 'character')
  stopifnot(class(model_name) == 'character')
  stopifnot(class(model_desc) == 'character')
  stopifnot(class(target) == 'character')
  stopifnot(class(tags) == 'character')
  stopifnot(class(url) == 'character')
  
  password = digest::digest(password, 'md5')
  
  hash = digest::digest(model, 'md5')
  
  url = paste0(url, "mogger/api/v1/model")
  
  # regex for finding out if model_name is a path
  if(!grepl('^[a-z0-9A-Z_]+$', model_name)) {
    stop("Your model's name contains non alphanumerical characters")
  }
  
  # gathering metadata
  body = list()
  
  # next data
  language = 'r'
  
  body[['modelName']] = model_name
  body[['modelDescription']] = model_desc
  body[['language']] = language
  body[["datasetId"]] = dataset_id
  body[["userName"]] = user_name
  body[['target']] = target
  body[['hash']] = hash
  
  # list of tags
  tags = as.vector(tags)
  body[['tags']] = tags
  print(body)
  # posting
  r = httr::POST(
    url=url,
    config=add_headers("userName"=user_name, "password"=password),
    body = body,
    encode='json')
  
  # return
  httr::content(r)
}
