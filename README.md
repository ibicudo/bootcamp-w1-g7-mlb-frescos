# Documentação Bootcamp MLB Wave1 (Grupo7)

---

## Informações básicas da aplicação

* Path base para requisições: **/api/v1/fresh-products/**
* A aplicação necessita autenticação para a utilização de seus recursos
* A documentação completa a respeito dos endpoints da aplicação está presente no **swagger-ui.html**

---

## Alterações propostas

### Mudanças em DTOs

**Se o endpoint não se encontra nessa lista, assume-se que ele segue os padrões da US**

**Endpoint**: POST /inboundorder \
Decidimos por não receber os códigos tanto da **InboundOrder** quanto das **Batch(s)** para a criação, deixando o Hibernate gerar os IDs para ambas entidades. \
Exemplo de entrada válida:

```
{
    "inboundOrder":{
        "orderDate": "2021-07-01", 
        "section": {
        "sectionCode": "CAJF001",
        "warehouseCode": "CAJF"
        }, 
        "batchStock":[
            {
            "productId": "51b3b287-0b78-484c-90c3-606c4bae9401",
            "currentTemperature": 10.0,
            "minimumTemperature": 5.0,
            "initialQuantity": 500,
            "currentQuantity": 500,
            "manufacturingDate": "2021-06-10",
            "manufacturingTime": "2021-06-10 18:30:49",
            "dueDate": "2021-12-15"
            },
            {
            "productId": "fa0d9b2e-3eac-417e-8ee6-f26037336522",
            "currentTemperature": 12.0,
            "minimumTemperature": 5.0,
            "initialQuantity": 500,
            "currentQuantity": 500,
            "manufacturingDate": "2021-06-10",
            "manufacturingTime": "2021-06-10 18:30:49",
            "dueDate": "2021-12-15"
            }
        ]
    }
}
```

---

### UserStory 001
**US-code:** ml-insert-batch-in-fulfillment-warehouse-01
#### Diagrama de sequência da criação de uma InboundOrder
[Link](https://mermaid-js.github.io/mermaid-live-editor/view/#eyJjb2RlIjoic2VxdWVuY2VEaWFncmFtXG4gICAgSW5ib3VuZE9yZGVyQ29udHJvbGxlci0-PitJbmJvdW5kT3JkZXJTZXJ2aWNlOiBjcmVhdGVJbmJvdW5kT3JkZXIoKVxuICAgIEluYm91bmRPcmRlclNlcnZpY2UtPj4rU3VwZXJ2aXNvclJlcG9zaXRvcnk6IGZpbmRCeUlkKClcbiAgICBTdXBlcnZpc29yUmVwb3NpdG9yeS0-Pi1JbmJvdW5kT3JkZXJTZXJ2aWNlOiBsb2dnZWQgc3VwZXJ2aXNvclxuICAgIEluYm91bmRPcmRlclNlcnZpY2UtPj4rU2VjdGlvblJlcG9zaXRvcnk6IGZpbmRCeUlkKClcbiAgICBTZWN0aW9uUmVwb3NpdG9yeS0-Pi1JbmJvdW5kT3JkZXJTZXJ2aWNlOiBzZWN0aW9uXG4gICAgSW5ib3VuZE9yZGVyU2VydmljZS0-PitQcm9kdWN0UmVwb3NpdG9yeTogZmluZEFsbEJ5SWQoKVxuICAgIFByb2R1Y3RSZXBvc2l0b3J5LT4-LUluYm91bmRPcmRlclNlcnZpY2U6IHByb2R1Y3RzXG4gICAgSW5ib3VuZE9yZGVyU2VydmljZS0-PiBJbmJvdW5kT3JkZXJTZXJ2aWNlOiBWYWxpZGF0aW9uc1xuICAgIEluYm91bmRPcmRlclNlcnZpY2UtPj4rSW5ib3VuZE9yZGVyUmVwb3NpdG9yeTogc2F2ZSgpXG4gICAgSW5ib3VuZE9yZGVyUmVwb3NpdG9yeS0-PitCYXRjaFJlcG9zaXRvcnk6IHNhdmVBbGwoKVxuICAgIEJhdGNoUmVwb3NpdG9yeS0-Pi1JbmJvdW5kT3JkZXJSZXBvc2l0b3J5OiBjcmVhdGVkXG4gICAgSW5ib3VuZE9yZGVyUmVwb3NpdG9yeS0-Pi1JbmJvdW5kT3JkZXJTZXJ2aWNlOiBjcmVhdGVkXG5cbiAgICBJbmJvdW5kT3JkZXJTZXJ2aWNlLT4-LUluYm91bmRPcmRlckNvbnRyb2xsZXI6IENyZWF0ZWQgMjAxXG4iLCJtZXJtYWlkIjoie1xuICBcInRoZW1lXCI6IFwiZGVmYXVsdFwiXG59IiwidXBkYXRlRWRpdG9yIjp0cnVlLCJhdXRvU3luYyI6dHJ1ZSwidXBkYXRlRGlhZ3JhbSI6dHJ1ZX0)

#### Diagrama de sequência da atualização de uma InboundOrder
[Link](https://mermaid-js.github.io/mermaid-live-editor/view/#eyJjb2RlIjoic2VxdWVuY2VEaWFncmFtXG4gICAgSW5ib3VuZE9yZGVyQ29udHJvbGxlci0-PitJbmJvdW5kT3JkZXJTZXJ2aWNlOiBjcmVhdGVJbmJvdW5kT3JkZXIoKVxuICAgIEluYm91bmRPcmRlclNlcnZpY2UtPj4rSW5ib3VuZE9yZGVyUmVwb3NpdG9yeTogZmluZEJ5SWQoKVxuICAgIEluYm91bmRPcmRlclJlcG9zaXRvcnktPj4tSW5ib3VuZE9yZGVyU2VydmljZTogSW5ib3VuZE9yZGVyXG4gICAgSW5ib3VuZE9yZGVyU2VydmljZS0-PiBJbmJvdW5kT3JkZXJTZXJ2aWNlOiBWYWxpZGF0ZSBpZiBvcmRlciBleHNpdHNcbiAgICBJbmJvdW5kT3JkZXJTZXJ2aWNlLT4-K0JhdGNoUmVwb3NpdG9yeTogZmluZEFsbEJ5SWQoKVxuICAgIEJhdGNoUmVwb3NpdG9yeS0-Pi1JbmJvdW5kT3JkZXJSZXBvc2l0b3J5OiBiYXRjaGVzXG4gICAgSW5ib3VuZE9yZGVyUmVwb3NpdG9yeS0-PitJbmJvdW5kT3JkZXJTZXJ2aWNlOiByZXF1ZXN0ZWQgYmF0Y2hlc1xuICAgIEluYm91bmRPcmRlclNlcnZpY2UtPj4gSW5ib3VuZE9yZGVyU2VydmljZTogVmFsaWRhdGUgaWYgdGhlIHJlcXVlc3RlZCBiYXRjaChlcykgYXJlIGZyb20gdGhlIEluYm91bmRPcmRlclxuICAgIEluYm91bmRPcmRlclNlcnZpY2UtPj4rU3VwZXJ2aXNvclJlcG9zaXRvcnk6IGZpbmRCeUlkKClcbiAgICBTdXBlcnZpc29yUmVwb3NpdG9yeS0-Pi1JbmJvdW5kT3JkZXJTZXJ2aWNlOiBsb2dnZWQgc3VwZXJ2aXNvclxuICAgIEluYm91bmRPcmRlclNlcnZpY2UtPj4rU2VjdGlvblJlcG9zaXRvcnk6IGZpbmRCeUlkKClcbiAgICBTZWN0aW9uUmVwb3NpdG9yeS0-Pi1JbmJvdW5kT3JkZXJTZXJ2aWNlOiBzZWN0aW9uXG4gICAgSW5ib3VuZE9yZGVyU2VydmljZS0-PitQcm9kdWN0UmVwb3NpdG9yeTogZmluZEFsbEJ5SWQoKVxuICAgIFByb2R1Y3RSZXBvc2l0b3J5LT4-LUluYm91bmRPcmRlclNlcnZpY2U6IHByb2R1Y3RzXG4gICAgSW5ib3VuZE9yZGVyU2VydmljZS0-PiBJbmJvdW5kT3JkZXJTZXJ2aWNlOiBWYWxpZGF0aW9uc1xuICAgIEluYm91bmRPcmRlclNlcnZpY2UtPj4rSW5ib3VuZE9yZGVyUmVwb3NpdG9yeTogdXBkYXRlKClcbiAgICBJbmJvdW5kT3JkZXJSZXBvc2l0b3J5LT4-K0JhdGNoUmVwb3NpdG9yeTogdXBkYXRlQWxsKClcbiAgICBCYXRjaFJlcG9zaXRvcnktPj4tSW5ib3VuZE9yZGVyUmVwb3NpdG9yeTogdXBkYXRlZFxuICAgIEluYm91bmRPcmRlclJlcG9zaXRvcnktPj4tSW5ib3VuZE9yZGVyU2VydmljZTogdXBkYXRlZFxuXG4gICAgSW5ib3VuZE9yZGVyU2VydmljZS0-Pi1JbmJvdW5kT3JkZXJDb250cm9sbGVyOiBDcmVhdGVkIDIwMVxuIiwibWVybWFpZCI6IntcbiAgXCJ0aGVtZVwiOiBcImRlZmF1bHRcIlxufSIsInVwZGF0ZUVkaXRvciI6dHJ1ZSwiYXV0b1N5bmMiOnRydWUsInVwZGF0ZURpYWdyYW0iOnRydWV9)


### UserStory 0005