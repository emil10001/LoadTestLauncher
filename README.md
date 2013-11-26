This project launches [the LoadTester app](https://github.com/emil10001/LoadTester). This is just to demonstrate how to launch and kill the other app. 

Here's how you would launch the LoadTester:

    Intent launchLoadTest = new Intent();
    launchLoadTest.setAction("com.feigdev.loadtester.api");
    long keepAlive = 20 * 1000;
    launchLoadTest.putExtra("KEEP_ALIVE", keepAlive);
    launchLoadTest.putExtra("CPU_ENABLED", true);
    launchLoadTest.putExtra("RAM_ENABLED", true);
    launchLoadTest.putExtra("NET_ENABLED", true);
    launchLoadTest.putExtra("MODE", LOW");
    sendBroadcast(launchLoadTest);

And, when you're done, you can kill it too:

    Intent launchLoadTest = new Intent();
    launchLoadTest.setAction("com.feigdev.loadtester.api");
    launchLoadTest.putExtra("KILL_NOW", "KILL_NOW");
    sendBroadcast(launchLoadTest);

[LoadTester source](https://github.com/emil10001/LoadTester)
