


$ns_ at 0.5 "$node_($source) add-mark m blue square"
$ns_ at 0.5 "$node_($dest) add-mark m magenta square"

$ns_ at 0.5 "$node_($source) label SENDER"
$ns_ at 0.5 "$node_($dest) label RECEIVER"


$ns_ at 0.01 "$ns_ trace-annotate \"Network Deployment\""
