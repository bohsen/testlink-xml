<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="xml" indent="yes" />
    <!-- Value encoding-->
    <xsl:output encoding="UTF-8" />

    <!-- Bookplan versions parameter -->
    <xsl:param name="version" select="rss/channel/title" />

    <!-- Testsuite template -->
    <xsl:template match="/">
        <testsuite xsl:use-attribute-sets="testsuite">
            <node_order>
                <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                13
                <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
            </node_order>
            <details>
                <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                <p>
                    <i>
                        Denne testsuite indeholder testcases til de nye features/forbedringer og fejlrettelser der kommer med <xsl:value-of select="$version" />
                    </i>
                </p>
                <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
            </details>
            <testsuite id="" name="Nye features og forbedringer" >
                <node_order>
                    <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                    1
                    <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                </node_order>
                <details>
                    <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                    <p>
                        <i>
                            Denne testsuite indeholder testcases til de nye features og forbedringer der kommer med <xsl:value-of select="$version" />
                        </i>
                    </p>
                    <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                </details>
            </testsuite>
            <testsuite id="" name="Fejlrettelser" >
                <node_order>
                    <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                    2
                    <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                </node_order>
                <details>
                    <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                    <p>
                        <i>
                            Denne testsuite indeholder testcases til de fejlrettelser der kommer med <xsl:value-of select="$version" />
                        </i>
                    </p>
                    <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                </details>
                <xsl:for-each select="rss/channel/item">
                    <xsl:variable name="i" select="position()-1" />
                    <testcase xsl:use-attribute-sets="testcase">
                        <node_order>
                            <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                            <xsl:value-of select="$i"/>
                            <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                        </node_order>
                        <version>
                            <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                            1
                            <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                        </version>
                        <summary>
                            <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                            <table align="left" border="1" cellpadding="1" cellspacing="0" style="width: 800px;">
                                <tbody>
                                    <tr>
                                        <td width="150">Formål</td>
                                        <td>
                                            At verificere ny feature/fejlrettelse - se
                                            <a xsl:use-attribute-sets="link">
                                                <xsl:value-of select="key" />
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> Kommentarer</td>
                                        <td>
                                            <xsl:value-of select="summary" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> Systemer</td>
                                        <td> </td>
                                    </tr>
                                    <tr>
                                        <td> Referencer</td>
                                        <td> </td>
                                    </tr>
                                </tbody>
                            </table>
                            <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                        </summary>
                        <preconditions>
                            <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                            <table align="left" border="1" cellpadding="1" cellspacing="0" style="width: 800px;">
                                <tbody>
                                    <tr>
                                        <td width="150"> Forudsætninger</td>
                                        <td> </td>
                                    </tr>
                                    <tr>
                                        <td> Udgangspunkt/Steps, der skal være foretaget forud for testen</td>
                                        <td> </td>
                                    </tr>
                                    <tr>
                                        <td> ﻿Test Data</td>
                                        <td> </td>
                                    </tr>
                                    <tr>
                                        <td> Brugere</td>
                                        <td> </td>
                                    </tr>
                                </tbody>
                            </table>
                            <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                        </preconditions>
                        <execution_type>
                            <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                            1
                            <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                        </execution_type>
                        <importance>
                            <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
                            3
                            <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
                        </importance>
                        <estimated_exec_duration>45.00</estimated_exec_duration>
                        <status>1</status>
                    </testcase>
                </xsl:for-each>
            </testsuite>
        </testsuite>
    </xsl:template>

    <!-- Attribute set testuite -->
    <xsl:attribute-set name="testsuite">
        <xsl:attribute name="id"/>
        <xsl:attribute name="name">
            <xsl:value-of select="rss/channel/title" />
        </xsl:attribute>
    </xsl:attribute-set>

    <!-- Attribute set testcase -->
    <xsl:attribute-set name="testcase">
        <xsl:attribute name="internalid"/>
        <xsl:attribute name="name">
            <xsl:value-of select="title" />
        </xsl:attribute>
    </xsl:attribute-set>

    <!-- Attribute set testcase -->
    <xsl:attribute-set name="link">
        <xsl:attribute name="href">
            <xsl:value-of select="link" />
        </xsl:attribute>
        <xsl:attribute name="target">
            <xsl:text disable-output-escaping="yes">_blank</xsl:text>
        </xsl:attribute>
    </xsl:attribute-set>

</xsl:stylesheet>
