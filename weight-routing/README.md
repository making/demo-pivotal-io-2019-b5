```
APP_NAME=demo
SPACE_NAME=demo

MESH_DOMAIN=mesh.apps.pks.aws.pcfjp.com
```

```
cf push --no-route

cf create-route ${SPACE_NAME} ${MESH_DOMAIN} -n ${APP_NAME}

APP_GUID=$(cf app ${APP_NAME} --guid)
ROUTE_GUID=$(cf curl /v2/routes?q=host:${APP_NAME} | jq -r .resources[0].metadata.guid)

cf curl /v3/route_mappings -X POST \
   -d "{\"relationships\":{\"app\":{\"guid\":\"${APP_GUID}\"},\"route\":{\"guid\":\"${ROUTE_GUID}\"}},\"weight\":64}"
```

```
while true;do date;curl -k -s https://demo.mesh.apps.pks.aws.pcfjp.com;sleep 0.5;done
```

Update `index.php`

```
cf push ${APP_NAME}-v2 --no-route

APP_GUID=$(cf app ${APP_NAME}-v2 --guid)

# v1:v2 = 4:1
cf curl /v3/route_mappings -X POST \
  -d "{\"relationships\":{\"app\":{\"guid\":\"${APP_GUID}\"},\"route\":{\"guid\":\"${ROUTE_GUID}\"}},\"weight\":16}"

# v1:v2 = 1:2
cf curl /v3/route_mappings/$(cf curl /v3/apps/${APP_GUID}/route_mappings | jq -r '.resources[0].guid') \
  -X PATCH -d '{"weight": 128}'
```

```
cf unmap-route ${APP_NAME} ${MESH_DOMAIN} -n ${APP_NAME}
cf d -f ${APP_NAME}
cf rename ${APP_NAME}-v2 ${APP_NAME}
```