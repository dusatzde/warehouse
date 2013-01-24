package cz.cvut.warehouse.util;


import cz.cvut.warehouse.model.EntityObject;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.el.ValueExpression;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Utilities for Facelets
 *
 */
@Named("FacesUtil")
public class FacesUtil {

    private static final String SPACE = " ";
    private static final String SPACE_WORD = "SPACE";
    /**
     * Declare and initialize log attribute
     */
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(FacesUtil.class);

    
    /**
     * Returning currently application uri.
     *
     * @return application uri
     */
    public static String getApplicationUri() {
        try {
            FacesContext ctxt = FacesContext.getCurrentInstance();
            ExternalContext ext = ctxt.getExternalContext();
            URI uri = new URI(ext.getRequestScheme(), null, ext.getRequestServerName(),
                              ext.getRequestServerPort(),
                              ext.getRequestContextPath(), null, null);
            return uri.toASCIIString();
        } catch (URISyntaxException e) {
            throw new FacesException(e);
        }
    }

    /**
     * replace space "word word" -> "wordSPACEword"
     *
     * @param text
     * @return text without spaces
     */
    public static String replaceSpaceJSPattern(String text) {
        return text.replace(SPACE, SPACE_WORD);
    }

    /**
     * Evaluates the given el, returning null if the value binding is null.
     *
     * @param el
     * @return the value from evaluating the given el.
     */
    public static Object eval(String el) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ValueExpression binding = ctx.getApplication().getExpressionFactory()
                .createValueExpression(ctx.getELContext(), el, Object.class);

        return binding == null ? null : binding.getValue(ctx.getELContext());
    }

    public static <T extends EntityObject> Collection<T> wrapCollection(final Collection<T> set) {
        List<T> result = new ArrayList<T>();
        if (set != null) {
            result = new ArrayList<T>(set);
        }
        return result;
    }

    public static <T> T getValue(T value, T def) {
        return value == null ? def : value;
    }

    /**
     * Sorts the given collection by the String value of the given bean
     * property. Property may be null, in which case the result is the same as
     * wrapping the given collection in a list.
     *
     * @param <T>
     * @param set
     * @param property
     * @return the sorted list
     * @see BeanUtils#getProperty(Object, String)
     */
    public static <T> List<T> sort(final Collection<T> set, final String property) {
        if (set == null) {
            return null;
        }
        if (set.isEmpty()) {
            return new ArrayList<T>();
        }
        List<T> list = new ArrayList<T>(set);

        if (property != null && !property.isEmpty()) {
            Collections.sort(list, new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    try {
                        if (BeanUtils.getProperty(o1, property) == null
                                && BeanUtils.getProperty(o2, property) == null) {
                            return 0;
                        } else if (BeanUtils.getProperty(o1, property) == null) {
                            return -1;
                        } else if (BeanUtils.getProperty(o2, property) == null) {
                            return 1;
                        }
                        return BeanUtils.getProperty(o1, property).toLowerCase()
                                .compareTo(BeanUtils.getProperty(o2, property).toLowerCase());
                    } catch (Exception e) {
                        LOG.error(e);
                    }
                    return 0;
                }
            });
        } else {
            Collections.sort(list, new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    try {
                        if (o1 == null && o2 == null) {
                            return 0;
                        } else if (o1 == null) {
                            return 1;
                        } else if (o2 == null) {
                            return -1;
                        } else {
                            return o1.toString().compareTo(o2.toString());
                        }
                    } catch (Exception e) {
                        LOG.error(e);
                    }
                    return 0;
                }
            });
        }
        return list;
    }

    /**
     *
     * Abbreviate long values ahoj ty koni jeden chlupatej => ahoj ty ...
     *
     * @param value
     * @param ending
     * @param length
     * @return
     */
    // TODO Fix the spelling of this method. Be careful, because it is invoked
    // in the front-end.
    public static String abbreviate(String value, String ending, String lengthString) {
        Integer length = Integer.parseInt(lengthString);

        // shorter than ending
        if (ending.length() > length) {
            return "";
        }

        if ((value.length()) <= length) {
            return value;
        } else if ((value.length() + ending.length()) > length) {
            return value.substring(0, length - ending.length()) + ending;
        } else {
            return value;
        }
    }

    /**
     * Returns the first element in the specified collection.
     *
     * @param c the collection
     * @return the first element in the specified collection, or null if the
     * collection is null or empty.
     */
    public static Object first(Collection<?> c) {
        return (c == null || c.isEmpty()) ? null : c.iterator().next();
    }

    @SuppressWarnings("unused")
	private static Object getBeanByName(String name) {

        BeanManager bm = getBeanManager();
        Set<Bean<?>> beansFound = bm.getBeans(name);
        if (!beansFound.isEmpty()) {
            Bean<?> bean = bm.getBeans(name).iterator().next();
            CreationalContext<?> ctx = bm.createCreationalContext(bean);
            // could be inlined below
            Object o = bm.getReference(bean, bean.getClass(), ctx); // could be
            // inlined with return
            return o;
        } else {
            return null;
        }
    }

    private static BeanManager getBeanManager() {
        try {
            InitialContext initialContext = new InitialContext();
            return (BeanManager) initialContext.lookup("java:comp/BeanManager");
        } catch (NamingException e) {
            return null;
        }
    }

    /**
     * Splits the string.
     *
     * @param s
     * @param regex
     * @return
     */
    public static String[] split(String s, String regex) {
        return s.split(regex);
    }

    public static boolean instanceOf(Object o, String type) {
        if (o == null || o.getClass() == null) {
            return false;
        }
        return o.getClass().getSimpleName().equals(type);
    }

    public static boolean isIE() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String browser = fc.getExternalContext().getRequestHeaderMap().get("user-agent");

        if (browser == null) {
            return false;
        } else if (browser.contains("Firefox")) {
            return false;
        } else if (browser.contains("Opera")) {
            return false;
        } else if (browser.contains("Safari")) {
            return false;
        } else if (browser.contains("Konqueror")) {
            return false;
        } else if (browser.contains("MSIE")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Concatenates at least two strings. The strings are obtained from the
     * {@linkplain Object#toString() string representations} of the given
     * {@link Object}s.
     *
     * @param a the first object
     * @param b the second object
     * @param c optional additional objects, in order
     * @return the concatenation of the string representations of the given
     * objects; or null if any parameter is null.
     */
    public static String concat(Object a, Object b, Object c) {
        if (a == null || b == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        if (a != null) {
            builder.append(a.toString());
        }
        if (b != null) {
            builder.append(b.toString());
        }
        if (c != null) {
            builder.append(c.toString());
        }
        return builder.toString();
    }

    /**
     * Capitalizes the first character of the specified string. If the first
     * character is already capitalized or is a non-alpha character, or if the
     * string is empty or null, the returned value is equal to the string.
     *
     * @param string the string to capitalize
     * @return a copy of the specified string with the first character
     * capitalized.
     */
    public static String capitalize(String string) {
        return (string == null || string.isEmpty()) ? string : string.substring(0, 1).toUpperCase()
                + string.substring(1);
    }

    /**
     * Simple string function toLowerCase
     *
     * @param a
     * @return
     */
    public static String toLowerCase(String a) {
        return a.toLowerCase();
    }

    /**
     * replace space "Ahoj voe" -> "Ahoj_voe"
     *
     * @param text
     * @return
     */
    public static String replaceSpace(String text) {
        return text.replaceAll(" ", "_");
    }

    public static String nl2br(String text) {
        return text.replaceAll("\r\n", "<br/>");
    }

    public static String replace(String text, String from, String to) {
        return text.replaceAll(from, to);
    }

    public static int length(Object o) {
        return o == null ? 0 : o.toString().length();
    }

    public static Integer max(Integer a, Integer b) {
        return Math.max(a, b);
    }

    public static Integer min(Integer a, Integer b) {
        return Math.min(a, b);
    }

    /**
     * Makes a html color from a random String
     *
     * @param arg
     * @return
     */
    public static String toHex(String arg) {
        String out = "cccccc";
        if (arg == null || arg.isEmpty()) {
            return out;
        }
        out = String.format("%x",
                (new BigInteger(arg.getBytes()).multiply(new BigInteger("113")).multiply(new BigInteger("17")
                .multiply(new BigInteger("11")).multiply(new BigInteger("31")))));
        StringBuffer buf = new StringBuffer().append(out);

        out = buf.reverse().toString();

        if (out.length() < 6) {
            out += "cccccc";
        }
        if (out.length() > 6) {
            out = out.substring(0, 6);
        }

        return out;
    }
}
