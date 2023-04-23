import 'package:flutter/material.dart';
import 'get_name_page.dart';
import 'get_color_page.dart';

final TextEditingController _controller = TextEditingController();

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String resultText = '';
  Color resultColor = Colors.transparent;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              onPressed: () async {
                final result = await Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => GetNamePage(),
                  ),
                );

                if (result != null) {
                  setState(() {
                    resultText = result;
                    _controller.text = resultText;
                  });
                }
              },
              child: const Text('Get Ones Name'),
            ),
            const SizedBox(height: 10),
            TextField(
              controller: _controller,
              readOnly: true,
              maxLines: 2,
              textAlign: TextAlign.center,
              decoration: InputDecoration(
                filled: true,
                fillColor: resultColor,
                hintText: 'Result will be shown here',
              ),
            ),
            const SizedBox(height: 10),
            ElevatedButton(
              onPressed: () async {
                final result = await Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => GetColorPage(),
                  ),
                );
                if (result != null) {
                  setState(() {
                    resultColor = result as Color;
                  });
                }
              },
              child: const Text('Get Ones Color'),
            ),
          ],
        ),
      ),
    );
  }
}
