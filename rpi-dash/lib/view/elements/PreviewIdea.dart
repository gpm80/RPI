import 'package:flutter/material.dart';
import 'package:rpi_dash/common/BoxStyle.dart';
import 'package:rpi_dash/model/Idea.dart';

class PreviewIdea extends StatelessWidget {
  final Idea idea;
  final void Function() changeListener;

  PreviewIdea(this.idea, this.changeListener);

  factory PreviewIdea.of(Idea idea, void Function() call) {
    return PreviewIdea(idea, call);
  }

  @override
  Widget build(BuildContext context) {
    if (idea == null) {
      return Center(child: Text('Не выбрано'));
    }
    return BoxStyle.commonCard(ListView(
      children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            _actionButton(),
            Text(idea.title,
                style: DefaultTextStyle.of(context).style.apply(
                    fontSizeFactor: 1.7,
                    color: Theme.of(context).primaryColor)),
            Text(idea.typeDesc,
                style: DefaultTextStyle.of(context).style.apply(
                      fontSizeFactor: 0.8,
                    )),
            Text('Описание',
                style: DefaultTextStyle.of(context).style.apply(
                      fontWeightDelta: 1,
                      fontSizeFactor: 1.2,
                    )),
            Card(
              child: Container(
                  padding: EdgeInsets.all(5.0),
                  child: Text(idea.description,
                      style: TextStyle(fontStyle: FontStyle.italic))),
            ),
            _attachments(context),
            Row(mainAxisAlignment: MainAxisAlignment.end, children: [
              _createButton('Утвердить', Colors.green, () {
                idea.state = "APPROVE";
                changeListener();
              }),
              _createButton('Закрыть', Colors.black26, () {
                changeListener();
              })
            ]),
          ],
        ),
      ],
    ));
  }

  Widget _actionButton() {
    return Container(
        padding: EdgeInsets.all(10.0),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            _createButton('На доработку', Colors.yellow, () {
              idea.state = "REVISION";
              changeListener();
            }),
            _createButton('Отклонить', Colors.red, () {
              idea.state = "DECLINED";
              changeListener();
            }),
          ],
        ));
  }

  /// Конструктор кнопок
  Widget _createButton(String title, Color borderColor, Function() call) {
    return FlatButton(
        child: new Text(title),
        onPressed: call,
        color: new Color.fromRGBO(240, 240, 240, 1.0),
        shape: new RoundedRectangleBorder(
            side: new BorderSide(color: borderColor, width: 2.0),
            borderRadius: new BorderRadius.circular(30.0)));
  }

  /// кнопки загрузки
  Widget _attachments(BuildContext context) {
    Color color = Theme.of(context).primaryColor;
    return Container(
        padding: EdgeInsets.only(top: 5.0, bottom: 5.0),
        child: Row(children: [
          IconButton(
            icon: Icon(
              Icons.play_circle_filled,
              color: color,
              size: 35.0,
            ),
            onPressed: () {
              Scaffold.of(context).showSnackBar(SnackBar(
                content: Text("Файл сохранен на диск"),
              ));
            },
          ),
          IconButton(
            icon: Icon(
              Icons.attach_file,
              color: color,
              size: 35.0,
            ),
            onPressed: () {
              Scaffold.of(context).showSnackBar(SnackBar(
                content: Text("Файл сохранен на диск"),
              ));
            },
          )
        ]));
  }
}
