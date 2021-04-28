# mutant-detector

mutant-detector es un backend que evalúa dado un ADN con una estructura matricial N x N,
si el mismo le pertenece a un mutante o no.

**Correr aplicación local:**

Esta api está construida con JAVA 8 y se conecta a un motor de bases de datos postgres,
para correr la aplicación en local se deben configurar en el archivo application.yaml, 
los datos de conexión a base de datos (url, user, password); una vez configurados, se corre 
el servicio mediante el siguiente comando ejecutado en la raíz del proyecto:

    ./gradlew bootRun

**Servicios que expone:**

Esta api expone 2 servicios REST:

- Servicio para validar ADN mutante:

    url: https://20210427t204528-dot-snappy-catcher-311002.ue.r.appspot.com/mutant/
    método http: @POST
  
    Request body de ejemplo:
        
        {
           "dna":["ATGCGA","GTGCGA","ATATGA","ACACTA","GCCCTA","TCACTG"]
        }

    ResponseBody:

        {
            "timestamp": "2021-04-22T07:55:41.851+00:00",
            "status": 200,
            "error": "OK",
            "message": "dna belongs to mutant!",
            "path": "/mutant/"
        }

    Las posibles respuestas del API son:

        BAD_REQUEST(400) -> En caso de que existan errores en los datos de entrada con 
        un mensaje descriptivo en el atributo "message" del response body

        OK(200), "dna belongs to mutant!" -> En caso de encontrar un ADN
        mutante.

        FORBIDDEN(403), "dna belongs to human!") -> En caso de encontrar un 
        ADN humano.

        INTERNAL_SERVER_ERROR, "sorry, something has gone wrong, please try again" -> En caso
        de que exista un error no controlado.
    

- Servicio para validar estadísticas de ADN analizado y encontrado:

    url: https://20210427t204528-dot-snappy-catcher-311002.ue.r.appspot.com/stats/
  
    método http: @GET
  
    Response Body:

        {
            "countMutantDna": 2,
            "countHumanDna": 1,
            "ratio": 2.0
        }    
        
    Las posibles respuestas del API son:

        BAD_REQUEST(400) -> En caso de que existan errores en los datos de entrada con 
        un mensaje descriptivo en el atributo "message" del response body

        OK(200) -> Cuando es respuesta exitosa con el siguiente JSON -> 

            {
                "countMutantDna": 2, //Número de ADN distinto evaluado para mutantes.
                "countHumanDna": 1, //Número de ADN distinto evaluado para humanos.
                "ratio": 2.0 //Estadística de ADN mutante por ADN humano encontrado.
            }   

        INTERNAL_SERVER_ERROR, "sorry, something has gone wrong, please try again" -> En caso
        de que exista un error no controlado.
        
