import 'package:flutter/material.dart';

class TaskTile extends StatefulWidget {
  @override
  State<TaskTile> createState() => _TaskTileState();
}

class _TaskTileState extends State<TaskTile> {
  //String taskText;
  bool isChecked = false;

  _TaskTileState();

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(
        "lalala",
        //taskText,
        style: TextStyle(
            fontSize: 16.0,
            decoration:
                isChecked ? TextDecoration.lineThrough : TextDecoration.none),
      ),
      trailing: TileCheckBox(false, (bool checkboxState) {
        setState(() {
          print("LALALALALALALALAALALALALLA");
          // print(isChecked);
           isChecked = checkboxState;
          // print(isChecked);
        });
      }),
    );
  }
}

class TileCheckBox extends StatelessWidget {
  final bool isChecked;
  final Function toggleCheckBoxState;

  const TileCheckBox(this.isChecked, this.toggleCheckBoxState);

  @override
  Widget build(BuildContext context) {
    return Checkbox(
      activeColor: Colors.lightBlueAccent,
      value: isChecked,
      onChanged: toggleCheckBoxState,
    );
  }
}
