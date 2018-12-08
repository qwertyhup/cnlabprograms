BEGIN { c=0; }
{
	if($1 == "d") {
		c++;
	}
}
END {
	printf("Total number of packets dropped = %d\n",c);
}
