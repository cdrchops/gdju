-- obj = luajava.newInstance("java.lang.Object")
-- obj is now a reference to the new object
-- created and any of its methods can be accessed.

-- this creates a string tokenizer to the "a,b,c,d"
-- string using "," as the token separator.
strTk = luajava.newInstance("java.util.StringTokenizer", "a,b,c,d", ",")

while strTk:hasMoreTokens() do
    print(strTk:nextToken())
end

--sys = luajava.bindClass("java.lang.System")
--print ( sys:currentTimeMillis() )

-- this prints the time returned by the function.

--str = luajava.bindClass("java.lang.String")
--strInstance = luajava.new(str)

--button = luajava.newInstance("java.awt.Button", "execute")
--button_cb = {}
--function button_cb.actionPerformed(ev)
-- . . .
--end

--buttonProxy = luajava.createProxy("java.awt.ActionListener", button_cb)

--button:addActionListener(buttonProxy)

--luajava.loadLib("test.LoadLibExample", "open")
--eg.example(3)

--public static int open(LuaState L) throws LuaException {
--  L.newTable();
--  L.pushValue(-1);
--  L.setGlobal("eg");
--
--  L.pushString("example");
--
--  L.pushJavaFunction(new JavaFunction(L) {
--    /**
--     * Example for loadLib.
--     * Prints the time and the first parameter, if any.
--     */
--    public int execute() throws LuaException
--    {
--      System.out.println(new Date().toString());
--
--      if (L.getTop() > 1)
--      {
--        System.out.println(getParam(2));
--      }
--
--      return 0;
--    }
--  });
--
--  L.setTable(-3);
--
--  return 1;
--}