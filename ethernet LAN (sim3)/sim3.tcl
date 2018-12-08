set ns [new Simulator]

set tf [open sim3.tr w]
set nf [open sim3.nam w]

$ns trace-all $tf
$ns namtrace-all $nf

proc finish {} {
	global ns tf nf
	$ns flush-trace
	close $tf
	close $nf
	exec nam sim3.nam &
	exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$n0 label "src1"
$n0 color "magenta"
$n5 label "dest1"
$n5 color "blue"

$n2 label "src2"
$n2 color "magenta"
$n3 label "dest2"
$n3 color "blue"

LanRouter set debug_ 0
$ns make-lan "$n0 $n1 $n2 $n3 $n4" 100Mb 100ms LL Queue/DropTail Mac/802_3
$ns duplex-link $n4 $n5 1Mb 1ms DropTail

set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0
set ftp0 [new Application/FTP]
$ftp0 set packetSize_ 500
$ftp0 set interval_ 0.00001
$ftp0 attach-agent $tcp0
set sink5 [new Agent/TCPSink]
$ns attach-agent $n5 $sink5

set tcp2 [new Agent/TCP]
$ns attach-agent $n2 $tcp2
set ftp2 [new Application/FTP]
$ftp2 set packetSize_ 600
$ftp2 set interval_ 0.001
$ftp2 attach-agent $tcp2
set sink3 [new Agent/TCPSink]
$ns attach-agent $n3 $sink3

$ns connect $tcp0 $sink5
$ns connect $tcp2 $sink3

set file1 [open file1 w]
set file2 [open file2 w]

$tcp0 attach $file1
$tcp2 attach $file2

$tcp0 trace cwnd_
$tcp2 trace cwnd_

$ns at 0.1 "$ftp0 start"
$ns at 5 "$ftp0 stop"
$ns at 7 "$ftp0 start"
$ns at 14 "$ftp0 stop"
$ns at 0.3 "$ftp2 start"
$ns at 8 "$ftp2 stop"
$ns at 10 "$ftp2 start"
$ns at 15 "$ftp2 stop"
$ns at 16 "finish"

$ns run
