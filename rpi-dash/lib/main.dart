import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:rpi_dash/view/NotReleaseView.dart';

import 'View.dart';
import 'view/StatisticsView.dart';
import 'view/TopListView.dart';

void main() {
  runApp(MaterialApp(
    debugShowCheckedModeBanner: false,
    localizationsDelegates: [
      GlobalMaterialLocalizations.delegate,
      GlobalWidgetsLocalizations.delegate,
    ],
    locale: Locale('ru', 'RU'),
    supportedLocales: const <Locale>[Locale('ru', 'RU')],
    theme: ThemeData.light().copyWith(primaryColor: Color(0xFF360058)),
    initialRoute: '/',
    routes: _initRoutes(),
  ));
}

/// Инициализация роутов.
Map<String, WidgetBuilder> _initRoutes() {
  return {
    '/': (BuildContext context) => View('Статистика', () {
          return StatisticsView();
        }),
    '/top': (BuildContext context) => View('Топ решений', () {
          return TopListView();
        }),
    '/contest': (BuildContext context) => View('Конкурсы', () {
          return NotReleaseView();
        }),
  };
}

/// Общее меню.
class CommonMenu extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Drawer(
      child: new ListView(
        children: [
          DrawerHeader(
            decoration: BoxDecoration(
              color: Theme.of(context).primaryColor,
            ),
            child: Text(
              'RPI-админка',
              style: TextStyle(
                color: Colors.white,
                fontSize: 24,
              ),
            ),
          ),
          _createMenuItem(context, 'Статистика', '/', icon: Icons.bar_chart),
          _createMenuItem(context, 'Топ решений', '/top', icon: Icons.topic),
          _createMenuItem(context, 'Конкурсы', '/contest',
              icon: Icons.assistant_photo),
        ],
      ),
    );
  }

  /// Создает элемент меню.
  Widget _createMenuItem(BuildContext context, String caption, String route,
      {IconData icon = Icons.keyboard_arrow_right_sharp}) {
    return ListTile(
      title: Text(caption),
      leading: Icon(icon),
      onTap: () {
        Navigator.pushNamed(context, route);
      },
    );
  }
}
