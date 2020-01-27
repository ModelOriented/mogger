# mogger: Logger for Predictive Models

The application that allows storing the metadata of models and their audits in the database.
It also provides a web interface with visualizations of the database contents.
Storing and acquiring data is made possible through the REST API. 

## Components

- mogger - JAVA REST application (server with database)
- docker - docker file that allows to run this service on any machine
- docs - documentation with all the information
- python_package - Python client package to work with the service
- r_package - R client package to work with the service
- scripts - other useful files e.g. script that creates the database
- examples - Python/R examples

## Run 

Build a docker by typing in a mogger/docker directory:

```{bash}
sudo docker build -t mogger .
```

Run the container with:

```{bash}
sudo docker run mogger
```

Then by:

```{bash}
sudo docker inspect $id
```

where `$id` is your docker's id, you can check the app ip address.

Default `$id` is `172.17.0.2`. Docker `$id` is a hash from:

```{bash}
sudo docker ps
```

## Example
