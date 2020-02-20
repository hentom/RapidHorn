func main()
{
  const Int[] a;
  const Int alength;
  Int[] b;
  assume(0 <= alength);
  assume(alength < 10);

  Int i = 0;
  while(i < alength)
  {
    if (a[i] == 1)
    {
      b[i] = 1;
    }
    else
    {
      b[i] = 0;
    }
    i = i + 1;
  }
  
  Int j = 0;
  while(j < alength) {
    assert(((a[j] == 1) && (b[j] == 1)) || ((a[j] != 1) && (b[j] == 0)));
    j = j + 1;
  }
}
