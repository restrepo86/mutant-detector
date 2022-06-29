#!/bin/bash

export PREVIOUS_CONTAINERS_UP=$(docker ps -f name="${PROJECT_NAME}-" --format "{{.Names}}")

if [[ "x$PREVIOUS_CONTAINERS_UP" != "x" ]]
then
        echo "******* PREVIOUS_CONTAINERS_UP = ${PREVIOUS_CONTAINERS_UP} ********"
        docker rm -fv ${PREVIOUS_CONTAINERS_UP}
else
        echo "*** NO HAY APLICACIONES PARA DETENER DE NOMBRE -> $PROJECT_NAME- ***"
fi

