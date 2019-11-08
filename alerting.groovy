

from('knative:endpoint/alerting')
  .convertBodyTo(String.class)
  .to('telegram:bots?chatId=your-id-here')
  .transform().constant("")
