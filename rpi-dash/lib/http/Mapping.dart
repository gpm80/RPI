import 'dart:convert';

import 'package:rpi_dash/common/charts/BarTimeSeriesChart.dart';
import 'package:rpi_dash/common/charts/PieStatisticsChart.dart';
import 'package:rpi_dash/common/charts/SimpleTimeSeriesChart.dart';
import 'package:rpi_dash/model/Idea.dart';

class Mapping {
  /// маппинг TimeSeries
  static List<TimeSeries> mapTimeSeries(String json) {
    List<dynamic> obj = jsonDecode(json);
    var list = List<TimeSeries>();
    obj.forEach((item) {
      list.add(TimeSeries(DateTime.parse(item['date']), item['count']));
    });
    return list;
  }

  /// маппинг распределения весов предложений
  static List<PieWeight> mapWeight(String json) {
    List<dynamic> obj = jsonDecode(json);
    var list = List<PieWeight>();
    obj.forEach((item) {
      list.add(PieWeight(item['importance'], item['count']));
    });
    return list;
  }

  /// маппинг распределения за год
  static List<MonthSeries> mapMonth(String json) {
    List<dynamic> obj = jsonDecode(json);
    var list = List<MonthSeries>();
    obj.forEach((item) {
      list.add(MonthSeries(DateTime.parse(item['date']), item['count']));
    });
    return list;
  }

  static List<Idea> mapIdea(String json) {
    List<dynamic> obj = jsonDecode(json);
    var list = List<Idea>();
    obj.forEach((e) {
      list.add(Idea.fromJson(e));
    });
    return list;
  }
}
