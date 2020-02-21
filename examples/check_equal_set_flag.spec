func main()
{
  const Int[] a;
  const Int[] b;
  const Int length;
  assume(0 <= length);
  assume(length < 10);

  Int r = 0;
  Int i = 0;
  while(i < length)
  {
    if(a[i] != b[i])
    {
      r = 1;
    }
    i = i + 1;
  }
  
  if (r == 1)
  {
    Bool flag = false;
    Int j = 0;
    while(j < length)
    {
      if (a[j] != b[j])
      {
        flag = true;
      }
      j = j + 1;
    }
    assert(flag);
  }
  else
  {
    Int k = 0;
    while(k < length)
    {
      assert(a[k] == b[k]);
      k = k + 1;
    }
  }
}
