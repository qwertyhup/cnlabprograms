BEGIN { }
{
	if($6 == "cwnd_")
	{
		printf("%d\t%d\n", $1, $7)
	}
}
END {
}
