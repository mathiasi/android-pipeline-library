def call(int expectedCount) {
    def output = sh(script: "${env.ANDROID_HOME}platform-tools/adb devices", returnStdout: true)
    def outputLines = output
            .tokenize("\r?\n")
    def available = []
    outputLines
            .drop(1)
            .each {
        if (it.contains("device") && !it.contains("no device")) {
            available += it
        }
    }
    if (available.size() != expectedCount) {
        currentBuild.result = 'FAILURE'
        def message = "Currently available devices are ${available.size()}, but should be ${devices}\nAvailable devices:\n${available.join("\n")}"
        throw new IllegalStateException(message)
    }
}