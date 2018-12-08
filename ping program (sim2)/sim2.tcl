set ns [new Simulator]

set tf [open sim2.tr w]
set nf [open sim2.nam w]

$ns trace-all $tf
$ns namtrace-all $nf

proc finish {} {
	global ns tf nf
	$ns flush-trace
	close $tf
	close $nf
	exec nam sim2.nam &
	exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
$n4 shape box

$ns duplex-link $n0 $n4 1005Mb 1ms DropTail
$ns duplex-link $n1 $n4 50Mb 1ms DropTail
$ns duplex-link $n2 $n4 2000Mb 1ms DropTail
$ns duplex-link $n3 $n4 200Mb 1ms DropTail
$ns duplex-link $n4 $n5 1Mb 1ms DropTail

$ns queue-limit $n0 $n4 5
$ns queue-limit $n2 $n4 3
$ns queue-limit $n4 $n5 2

set p1 [new Agent/Ping]
set p2 [new Agent/Ping]
set p3 [new Agent/Ping]
set p4 [new Agent/Ping]
set p5 [new Agent/Ping]

$p1 set packetSize_ 50000
$p1 set interval_ 0.0001
$ns attach-agent $n0 $p1

$ns attach-agent $n1 $p2

$p3 set packetSize_ 30000
$p3 set interval_ 0.00001
$ns attach-agent $n2 $p3

$ns attach-agent $n3 $p4
$ns attach-agent $n5 $p5

$ns connect $p1 $p5
$ns connect $p3 $p4

Agent/Ping instproc recv {from rtt} {
	$self instvar node_
	puts "node [$node_ id] received ping from $from with round trip time $rtt msec"
}

for {set i 0.1} {$i < 1.0} {set i [expr $i+0.1]} {
	$ns at $i "$p1 send"
}

for {set i 0.1} {$i < 1.0} {set i [expr $i+0.1]} {
	$ns at $i "$p3 send"
}

$ns at 2.0 "finish"

$ns run
