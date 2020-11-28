import 'dart:convert';

import 'package:http/http.dart' as http;

class Request {
  /// Запрос статистики
  static httpStatistics(DateTime dateTime, int retro) async {
    print('request: time: $dateTime');
    String uri = 'http://localhost:8080/get';
    var _header = {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'Accept-Charset': 'utf-8'
    };
    var _body = jsonEncode(
        {'timePoint': dateTime.toIso8601String(), 'retroHour': retro});
    try {
      var response = await http.post(uri, headers: _header, body: _body);
      if (response.statusCode == 200) {
        var _result = response.body;
        print('body: $_result');
      } else {
        return 'statusCode: ${response.statusCode}';
      }
    } catch (err) {
      return err.toString();
    }
  }
}
