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
    return BoxStyle.commonCard(
      Container(
        child: Column(
          children: [
            _actionButton(),
            Text(idea.title,
                style: DefaultTextStyle.of(context).style.apply(
                      fontSizeFactor: 1.8,
                    )),
            Align(
                alignment: Alignment.topLeft,
                child: Text(idea.typeDesc,
                    style: DefaultTextStyle.of(context).style.apply(
                          fontSizeFactor: 1.2,
                        ))),
            Container(
              padding: EdgeInsets.only(top: 5.0),
              child: Text(
                'Описание',
                style: TextStyle(fontStyle: FontStyle.italic),
              ),
            ),
            Flexible(child: Text(idea.description)),
          ],
        ),
      ),
    );
  }

  Widget _actionButton() {
    return Container(
        padding: EdgeInsets.all(10.0),
        child: Row(
          children: [
            Expanded(
                child: _createButton('На доработку', Colors.yellow, () {
              idea.state = "Revision";
              changeListener();
            })),
            Expanded(
                child: _createButton('Отклонить', Colors.red, () {
              idea.state = "Reject";
              changeListener();
            })),
            Expanded(
                child: _createButton('На внедрение', Colors.green, () {
              idea.state = "ToWork";
              changeListener();
            })),
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
}
