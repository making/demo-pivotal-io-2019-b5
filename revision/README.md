```
cf push --no-start

APP_NAME=demo
cf curl /v3/apps/$(cf app ${APP_NAME} --guid)/features/revisions -X PATCH -d '{"enabled": true}'
cf start ${APP_NAME}
```

```
cf curl /v3/apps/$(cf app ${APP_NAME} --guid)/revisions | jq -r '.resources[] | {version, description, guid}'
```

```
while true;do date;curl -k -s https://demo.apps.pks.aws.pcfjp.com;sleep 0.5;done
```

Update `index.php`

```
cf v3-zdt-push ${APP_NAME} --wait-for-deploy-complete
```

```
cf curl /v3/apps/$(cf app ${APP_NAME} --guid)/revisions | jq -r '.resources[] | {version, description, guid}'
```

Update `index.php`


```
cf v3-zdt-push ${APP_NAME} --wait-for-deploy-complete
```

```
cf curl /v3/apps/$(cf app ${APP_NAME} --guid)/revisions | jq -r '.resources[] | {version, description, guid}'
```

Rollback to rev 2

```
REV_VESION=2
REV_GUID=$(cf curl /v3/apps/$(cf app ${APP_NAME} --guid)/revisions | jq -r ".resources[] | select(.version == ${REV_VESION}) | .guid")
cf curl /v3/deployments -X POST -d "{\"relationships\":{\"app\":{\"data\":{\"guid\":\"$(cf app ${APP_NAME} --guid)\"}}},\"revision\":{\"guid\":\"${REV_GUID}\"}}"
```