

from('paho:sensors?brokerUrl=tcp://mqtt-broker.osd19-backstage.svc.cluster.local:1883')
  .log('${body}')
  .multicast()
    .to('knative:channel/measures')
    .to('direct:check')
  .end()


from('direct:check')
  .unmarshal().json()
  .filter().simple('${body[max]} > 120')
  .setBody().simple('ALERT!!! Pressure too high: ${body[max]}')
  .to('knative:endpoint/alerting')

