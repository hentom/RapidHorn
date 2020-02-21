func main()
{
  Int[] a;

  const Int[] b;
  const Int blength;
  assume(0 <= blength);
  assume(blength < 3);

  Int i = 0;
  Int alength = 0;
  while(i < blength)
  {
    if (b[i] != 0)
    {
      a[alength] = b[i];
      alength = alength + 1;
    }
    i = i + 1;
  }
  
  Int j = 0;
  while(j < alength)
  {
    assert(a[j] != 0);
    
    Bool flag = false;
    Int k = 0;
    while(k < blength)
    {
      if (a[j] == b[k])
      {
        flag = true;
      }
      k = k + 1;
    }
    assert(flag);
    
    j = j + 1;
  }
}
