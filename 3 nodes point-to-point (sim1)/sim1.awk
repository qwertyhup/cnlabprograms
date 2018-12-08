BEGIN { c=0; }
{
	if($1 == "d") {
		c++;
		printf("%s\t%d\n",$5,$11);
	}
}
END {
	printf("Total number of packets dropped: %d\n",c);
}
