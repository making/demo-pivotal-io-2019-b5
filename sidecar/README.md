```
GOOS=linux go build ./cmd/sidecar/

cf v3-create-app demo
cf v3-apply-manifest -f manifest.yml
cf v3-push demo
```

```
curl -k https://demo.apps.pks.aws.pcfjp.com -w '\n'
```

```
curl -k https://demo.apps.pks.aws.pcfjp.com/kill-sidecar
```

```
   2019-04-18T16:29:33.85+0900 [APP/PROC/WEB/SIDECAR/DEMO/0] OUT Exit status 137
   2019-04-18T16:29:33.85+0900 [CELL/SSHD/0] OUT Exit status 0
   2019-04-18T16:29:33.87+0900 [APP/PROC/WEB/0] OUT Exit status 143
```

```
curl -k https://demo.apps.pks.aws.pcfjp.com/kill-main
```

```
   2019-04-18T16:32:22.04+0900 [APP/PROC/WEB/0] OUT Exit status 137
   2019-04-18T16:32:22.05+0900 [CELL/SSHD/0] OUT Exit status 0
   2019-04-18T16:32:22.06+0900 [APP/PROC/WEB/SIDECAR/DEMO/0] OUT Exit status 143
```