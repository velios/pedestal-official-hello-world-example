clj -M:dev

(require 'hello)

:dbg

(hello/start)

curl -i http://127.0.0.1:8890/greet