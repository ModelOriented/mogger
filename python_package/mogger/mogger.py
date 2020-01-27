from builtins import isinstance, bytes
from getpass import getpass
import os
import re
import requests
import hashlib
import pickle


def create_config(url):
    user_name = input('user: ')
    password = getpass('password: ')

    with open(".mogger.config", 'w') as fd:
        fd.write(user_name + '\n')
        fd.write(password + '\n')
        fd.write(url + '\n')


def get_config():
    with open('.mogger.config', 'r') as fd:
        user_name = fd.readline()[:-1]
        password = fd.readline()[:-1]
        url = fd.readline()[:-1]

    return user_name, password, url


def create_user(url=None):
    """Create user in mogger

    Params
    ------
    url : str
        url to mogger

    Returns
    -------
    TODO
    """
    if os.path.isfile('.mogger.config'):
        user_name, password, url = get_config()
    else:
        user_name = input('user: ')
        password = getpass('password: ')

    md5 = hashlib.md5()
    md5.update(bytes(password, 'utf8'))
    password = md5.hexdigest()

    url += "mogger/api/v1/user"

    info = {
        'userName': user_name,
        'password': password
    }

    # creating request
    r = requests.post(url, json=info, headers={'content-type': 'application/json;charset=UTF8'})

    return r

def upload_dataset(task,
                   dataset_name,
                   dataset_desc=None,
                   dataset_url=None,
                   url=None):
    """Register dataset in the mogger

    Params
    ------
    dataset_name : str
        name of the dataset
    dataset_desc : str, optional
        description of this dataset
    url : str
        url to your mogger

    Returns
    -------
    TODO
    """

    if os.path.isfile('.mogger.config'):
        user_name, password, url = get_config()
    else:
        user_name = input('user: ')
        password = getpass('password: ')

    if not isinstance(user_name, str):
        raise TypeError("user_name must be a string")
    if not isinstance(password, str):
        raise TypeError("password must be a string")
    if not isinstance(dataset_name, str):
        raise TypeError("dataset_name must be a string")
    if not isinstance(dataset_desc, str):
        raise TypeError("dataset_name must be a string")
    if not isinstance(url, str):
        raise TypeError("url must be a string")

    md5 = hashlib.md5()
    md5.update(bytes(password, 'utf8'))
    password = md5.hexdigest()

    url += "mogger/api/v1/dataset"

    info = \
        {
            'userName': user_name,
            'datasetName': dataset_name,
            "taskName": task,
            "url": dataset_url,
            'datasetDescription': dataset_desc
        }

    # creating request
    r = requests.post(url, json=info,
                      headers={'content-type': 'application/json;charset=UTF8',
                               'userName': user_name,
                               'password': password})

    return r


def upload_model(model,
                 dataset_id,
                 model_name,
                 target,
                 model_desc,
                 tags,
                 url=None):
    """Function uploads metadata of the machine learning model.

    Parameters
    ----------
    model: model
        model
    model_name : string
        name of the model
    dataset_id : integer
        name of the existing dataset in the mogger
    model_desc : string
        description of the model
    tags : list
        list of tags
    url : str
        url to your mogger

    Returns
    -------
    string
        id of the uploading

    Examples
    --------
    models.upload(model, 'Example_model', 'This is the example model', 'Species', ['example', 'easy'], iris, 'iris', 'Example dataset', 'req')
        -> user: Example user
        -> password:

    models.upload(model, 'Example_model', 'This is the example model', 'Species', ['example', 'easy'], 'aaaaaaaaaaaaaa', 'iris', 'Example dataset', 'req')
        -> user: Example user
        -> password:

    models.upload(model, 'Example_model', 'This is the example model', 'Species', ['example', 'easy'], 'aaaaaaaaaaaaaa', None, None, 'req')
        -> user: Example user
        -> password:
    """

    if os.path.isfile('.mogger.config'):
        user_name, password, url = get_config()
    else:
        user_name = input('user: ')
        password = getpass('password: ')

    if not isinstance(user_name, str):
        raise TypeError("user_name must be a string")
    if not isinstance(password, str):
        raise TypeError("password must be a string")
    if not isinstance(url, str):
        raise TypeError("url must be a string")
    if not isinstance(model_name, str):
        raise TypeError("model_name must be a string")
    if not isinstance(model_desc, str):
        raise TypeError("model_desc must be a string")
    if not isinstance(target, str):
        raise TypeError("target must be a string")
    if not isinstance(tags, list):
        raise TypeError("tags must be a list")

    md5 = hashlib.md5()
    md5.update(bytes(password, 'utf8'))
    password = md5.hexdigest()

    url += "mogger/api/v1/model"

    if re.search('^[a-z0-9A-Z_]+$', model_name) is None:
        return "Your model name contains non alphanumerical signs."

    model = pickle.dumps(model)
    model_md5 = hashlib.md5()
    model_md5.update(model)
    model = model_md5.hexdigest()

    # collecting system info
    info = {'language': 'python'}

    info['datasetId'] = dataset_id

    # user_name
    info['userName'] = user_name

    info['modelName'] = model_name

    info['hash'] = model

    info['target'] = target

    info['modelDescription'] = model_desc

    # tags
    info['tags'] = tags

    # creating request
    r = requests.post(url, json=info, headers={'content-type': 'application/json;charset=UTF8',
                                               'userName': user_name,
                                               'password': password})

    return r


def upload_audit(model_id,
                 dataset_id,
                 measure,
                 value,
                 url=None):
    """Function upload audits of the model

    Params
    ------
    model : str
        Name/id of the model
    measure : str, list
        name of the measure or list of the names of the measures used in audit
    score : float, array-like
        score or list of the scores in the same order as in measure parameter
    url : str
        url to mogger

    Returns
    -------

    """

    if os.path.isfile('.mogger.config'):
        user_name, password, url = get_config()
    else:
        user_name = input('user: ')
        password = getpass('password: ')

    if not isinstance(user_name, str):
        raise TypeError("user name must be string")
    if not isinstance(password, str):
        raise TypeError("password must be string")

    md5 = hashlib.md5()
    md5.update(bytes(password, 'utf8'))
    password = md5.hexdigest()

    url += "mogger/api/v1/audit"

    info = \
        {
            "modelId": model_id,
            "datasetId": dataset_id,
            'measure': measure,
            'value': value
        }

    r = requests.post(url, json=info,
                      headers={'content-type': 'application/json;charset=UTF8',
                               'userName': user_name,
                               'password': password})

    return r
