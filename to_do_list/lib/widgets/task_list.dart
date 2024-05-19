import 'package:flutter/material.dart';

import 'list_tile.dart';

class TaskList extends StatefulWidget {
  @override
  State<TaskList> createState() => _TaskListState();
}

class _TaskListState extends State<TaskList> {
  @override
  Widget build(BuildContext context) {
    return ListView(
      children: [
         // TaskTile(),
        // TaskTile(),
         TaskTile()
      ],
    );
  }
}
