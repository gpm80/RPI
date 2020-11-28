import 'dart:convert';

class TestSeries {
  static String timeSeries() {
    return jsonEncode([
      {'date': '2015-01-01', 'count': 30},
      {'date': '2016-01-01', 'count': 15},
      {'date': '2017-01-01', 'count': 120},
      {'date': '2018-01-01', 'count': 80},
      {'date': '2019-01-01', 'count': 150},
      {'date': '2020-01-01', 'count': 280},
    ]);
  }

  static String timeSeries2() {
    return jsonEncode([
      {'date': '2015-01-01', 'count': 12},
      {'date': '2016-01-01', 'count': 80},
      {'date': '2017-01-01', 'count': 12},
      {'date': '2018-01-01', 'count': 100},
      {'date': '2019-01-01', 'count': 150},
      {'date': '2020-01-01', 'count': 60},
    ]);
  }

  static String pieWeight() {
    return jsonEncode([
      {'importance': 'low', 'count': 120},
      {'importance': 'medium', 'count': 60},
      {'importance': 'fast', 'count': 10},
    ]);
  }

  static String monthSeries() {
    return jsonEncode([
      {'date': '2020-01-01', 'count': 10},
      {'date': '2020-02-01', 'count': 20},
      {'date': '2020-03-01', 'count': 30},
      {'date': '2020-04-01', 'count': 15},
      {'date': '2020-05-01', 'count': 10},
      {'date': '2020-06-01', 'count': 5},
      {'date': '2020-07-01', 'count': 1},
      {'date': '2020-08-01', 'count': 3},
      {'date': '2020-09-01', 'count': 20},
      {'date': '2020-10-01', 'count': 25},
      {'date': '2020-11-01', 'count': 30},
      {'date': '2020-12-01', 'count': 10},
    ]);
  }

  static String monthSeriesRelease() {
    return jsonEncode([
      {'date': '2020-01-01', 'count': 0},
      {'date': '2020-02-01', 'count': 1},
      {'date': '2020-03-01', 'count': 0},
      {'date': '2020-04-01', 'count': 2},
      {'date': '2020-05-01', 'count': 0},
      {'date': '2020-06-01', 'count': 5},
      {'date': '2020-07-01', 'count': 0},
      {'date': '2020-08-01', 'count': 0},
      {'date': '2020-09-01', 'count': 4},
      {'date': '2020-10-01', 'count': 0},
      {'date': '2020-11-01', 'count': 0},
      {'date': '2020-12-01', 'count': 0},
    ]);
  }

  static String ideaList() {
    return jsonEncode([
      {
        'title': 'Рацуха 1',
        'type': 'rele',
        'typeDesc': 'релейная защита и противоаварийная автоматика',
        'importance': 80,
        'collaborators': 60,
        'date': '2020-01-25',
        'description':
            'Авторский коллектив из семи человек представил свои рацпредложения в области автоматизации процессов, которые повышают производственную безопасность и надежность оборудования, обеспечивают бесперебойное электроснабжение потребителей, а также позволяют снижать затраты на технологические потери. В прошлом году сотрудниками службы предложено три проекта, которые уже находятся в опытной эксплуатации и рекомендованы к тиражированию в производственной деятельности компании'
      },
    ]);
  }
}
