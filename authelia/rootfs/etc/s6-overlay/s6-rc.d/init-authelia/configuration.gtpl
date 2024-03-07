---
###############################################################
#                Authelia minimal configuration               #
###############################################################

server:
  port: 9091

log:
  level: info

jwt_secret: {{ .jwt_secret }}

authentication_backend:
  file:
    path: /config/users.yml

session:
  secret: {{ .session_secret }}
  domain: {{ .domain }}
  expiration: 3600  # 1 hour
  inactivity: 300  # 5 minutes
  remember_me_duration: 1y

storage:
  encryption_key: {{ .storage_secret }}
  local:
    path: /config/db.sqlite
...