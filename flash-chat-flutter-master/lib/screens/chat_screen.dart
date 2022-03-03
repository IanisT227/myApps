import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flash_chat/constants.dart';
import 'package:flutter/material.dart';


FirebaseUser loggedUser;

class ChatScreen extends StatefulWidget {
  static String id = 'chat_screen';

  @override
  _ChatScreenState createState() => _ChatScreenState();
}

class _ChatScreenState extends State<ChatScreen> {
  final firestore = Firestore.instance;
  final _auth = FirebaseAuth.instance;
  final messageTextcontroller = TextEditingController();

  String messageText;

  @override
  void initState() {
    super.initState();
    getCurrentUser();
  }

  void getCurrentUser() async {
    try {
      final user = await _auth.currentUser();
      if (user != null) {
        loggedUser = user;
        print(loggedUser.email);
      }
    } catch (e) {
      print(e);
    }
  }

  void getMessages() async {
    final messages = await firestore.collection('messages').getDocuments();
    for (var message in messages.documents) {
      print(message.data);
    }
  }

  void messagesStream() async {
    await for (var snapshot in firestore.collection('messages').snapshots())
      for (var msg in snapshot.documents) {
        print(msg);
      }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: null,
        actions: <Widget>[
          IconButton(
              icon: Icon(Icons.close),
              onPressed: () {
                _auth.signOut();
                Navigator.pop(context);
              }),
        ],
        title: Text('⚡️Chat'),
        backgroundColor: Colors.lightBlueAccent,
      ),
      body: SafeArea(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            messageStream(),
            Container(
              decoration: kMessageContainerDecoration,
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  Expanded(
                    child: TextField(
                      onChanged: (value) {
                        messageText = value;
                      },
                      decoration: kMessageTextFieldDecoration,
                      controller: messageTextcontroller,
                    ),
                  ),
                  FlatButton(
                    onPressed: () {
                      messageTextcontroller.clear();
                      if (messageText.isNotEmpty)
                        firestore.collection('messages').add(
                            {'text': messageText, 'sender': loggedUser.email});
                    },
                    child: Text(
                      'Send',
                      style: kSendButtonTextStyle,
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class messageStream extends StatelessWidget {
  final firestore = Firestore.instance;

  @override
  Widget build(BuildContext context) {
    return StreamBuilder(
      stream: firestore.collection('messages').snapshots(),
      builder: (BuildContext context, AsyncSnapshot<QuerySnapshot> snapshot) {
        if (snapshot.hasData) {
          final messages = snapshot.data.documents.reversed;
          List<messageBubble> messageWidgets = [];
          for (var message in messages) {
            final messageText = message.data['text'];
            final messageSender = message.data['sender'];
            final currentuser = loggedUser.email;
              messageWidgets.add(messageBubble(
                  messageText, messageSender, currentuser == message.data['sender']));
          }
          return Expanded(
            child: ListView(
              padding: EdgeInsets.symmetric(vertical: 20.0, horizontal: 10.0),
              children: messageWidgets,
              reverse: true,
            ),
          );
        } else
          return Center(
            child: CircularProgressIndicator(
              backgroundColor: Colors.lightBlueAccent,
            ),
          );
      },
    );
  }
}

class messageBubble extends StatelessWidget {
  final String messageText;
  final String messageSender;
  final bool isUser;

  messageBubble(this.messageText, this.messageSender, this.isUser);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(10.0),
      child: Column(
        crossAxisAlignment: isUser ? CrossAxisAlignment.end : CrossAxisAlignment.start,
        children: [
        Text(
        messageSender,
        style: TextStyle(fontSize: 12.0, color: Colors.white70),
      ),
      Material(
          elevation: 5.0,
          borderRadius: isUser ? BorderRadius.only(topLeft: Radius.circular(30.0),
              bottomLeft: Radius.circular(30.0),
              bottomRight: Radius.circular(30.0))
              : BorderRadius.only(topRight: Radius.circular(30.0),
              bottomLeft: Radius.circular(30.0),
              bottomRight: Radius.circular(30.0)),
          color: isUser ? Colors.lightBlueAccent : Colors.white,
          child: Padding(
      padding:
      const EdgeInsets.symmetric(vertical: 10.0, horizontal: 20.0),
      child: Text(
        messageText,
        style: TextStyle(fontSize: 15.0,
        color: isUser ? Colors.white : Colors.black87),
      ),
    ),)
    ,
    ]
    ,
    )
    ,
    );
  }
}
