func main()
{
	Int[] a;
	const Int alength;
	const Int v;
	assume(alength <= 0);

	Int i = 0;
	while(i < alength)
	{
		a[i] = v;
		i = i + 1;
	}
	
	Int j = 0;
	while(j < alength)
	{
	    assert(a[j] == v);
	    j = j + 1;
	}
}
