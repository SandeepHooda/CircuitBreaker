# CircuitBreaker
Step 1: launch  https://circuit-breaker-mvn.herokuapp.com/actuator/health. This may take some time becuse it is the first call to the application. On this health page the status will be state":"CLOSED" <br/>
Step 2: Hit this page and keep refreshing it again and again https://circuit-breaker-mvn.herokuapp.com/order<br/>
<b>You will observe: </b><br/>
  &nbsp;&nbsp;&nbsp; First 3 calls will be fast and you will receive mesage "Downstream service is working fine". This is because fault will be intruduced after three calls <br/>
  &nbsp;&nbsp;&nbsp; Call # 4,5,6 will be slow because now fault has been introduces in the system but the circuit breaker has not isolated the faulty service <br/>
  &nbsp;&nbsp;&nbsp; Call 7 onwards the fault will still exist but the response will be fast because circuit breaker has detected that there is some issue in downstream service hence better not call that and directy return error. if you check the health page the status will reflect as "state":"OPEN" <br/>
      
