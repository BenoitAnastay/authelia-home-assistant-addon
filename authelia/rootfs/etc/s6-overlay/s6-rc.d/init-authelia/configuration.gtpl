# yamllint disable rule:line-length
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

{{ if not .smtp_host }}
notifier:
  filesystem:
    filename: /config/emails.txt
{{ else }}
notifier:
  smtp:
    username: {{ .smtp_username }}
    # Password can also be set using a secret: https://www.authelia.com/configuration/methods/secrets/
    password: {{ .smtp_password }}
    sender: {{ .smtp_sender }}
    host: {{ .smtp_host }}
    port: {{ .smtp_port }}
{{ end }}

access_control:
  default_policy: bypass
  rules:
    - domain: "public.{{ .domain }}"
      policy: bypass
    - domain: "traefik.{{ .domain }}"
      policy: one_factor
...
# yamllint enable rule:line-length