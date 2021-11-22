# CircuitBreaker
Step 1: launch  https://circuit-breaker-mvn.herokuapp.com/actuator/health/circuitBreakers. This may take some time becuse it is the first call to the application. On this health page the status will be <b>state":"CLOSED" </b><br/>
Step 2: Hit this page and keep refreshing it again and again https://circuit-breaker-mvn.herokuapp.com/order<br/>
<b>You will observe: </b><br/>
  &nbsp;&nbsp;&nbsp; First 3 calls will be fast and you will receive mesage "Downstream service is working fine". This is because fault will be induced after third call <br/>
  &nbsp;&nbsp;&nbsp; Call # 4,5,6 will be slow because now fault has been introduced in the system but the circuit breaker has not isolated the faulty service. So application is waiting on the faulty system, hence wait of approx 5 seconds then you will see that application is down <br/>
  &nbsp;&nbsp;&nbsp; Call 7 onwards the fault will still exist but the response will be fast because circuit breaker has detected that there is some issue in downstream service and has isolated that from the main application. Due to this main application will not make a call to faulty system at all and will return the error immediately. if you check the health page the status will reflect as <b>"state":"OPEN"</b> <br/>
  &nbsp;&nbsp;&nbsp; After a configured amount of time (120 seconds in this case). The system will check the health of the faulty system and will but the isolated system back if it has turned healthly.
      
