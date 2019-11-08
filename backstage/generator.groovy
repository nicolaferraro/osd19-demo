import org.apache.camel.model.dataformat.JsonLibrary;

from('timer:tick')
  .bean(this, "randomPressure")
  .marshal().json(JsonLibrary.Jackson)
  .log('${body}')
  .to('paho:sensors?brokerUrl=tcp://mqtt-broker:1883')


def randomPressure() {
  p = new Data()
  p.type = "BP"
  p.sensor = java.util.UUID.randomUUID().toString()
  p.min = 55 + java.util.concurrent.ThreadLocalRandom.current().nextInt(30)
  p.max = 95 +  java.util.concurrent.ThreadLocalRandom.current().nextInt(30)
  return p
}

@groovy.transform.ToString
class Data {
  String type
  String sensor
  int min
  int max
}
