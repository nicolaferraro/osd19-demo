

from('knative:endpoint/alerting')
  .convertBodyTo(String.class)
  .to('telegram:bots?chatId=158584902')
  .setBody().constant(null)
