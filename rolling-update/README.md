```
cf push 
```

```
while true;do date;curl -k -s https://demo.apps.pks.aws.pcfjp.com;sleep 0.5;done
```

Update `index.php`


```
cf v3-zdt-push demo --wait-for-deploy-complete
```

```
while true;do date;cf app demo;sleep 0.5;done
```