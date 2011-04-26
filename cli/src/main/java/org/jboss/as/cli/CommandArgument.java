/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.cli;

/**
 *
 * @author Alexey Loubyansky
 */
public interface CommandArgument {

    /**
     * The default name of the argument.
     * An argument can have a few names, e.g. --force and -f.
     * @return  the default name of the argument.
     */
    String getDefaultName();

    /**
     * If the argument doesn't have a name its value can be found by index.
     * Indexes start with 0.
     * A command could have both a name and an index. In that case, the name is optional.
     * If the command doesn't have a fixed index, the method will return -1.
     * @return  the index of the argument.
     */
    int getIndex();

    /**
     * Checks whether the argument is present on the command line.
     * @param args  parsed arguments
     * @return  true if the argument is present, false - otherwise.
     */
    boolean isPresent(ParsedArguments args);

    /**
     * Checks whether the argument can appear on the command
     * given the already present arguments. (Used for tab-completion.)
     * @param args parsed arguments
     * @return true if the argument can appear on the command line next, false - otherwise.
     */
    boolean canAppearNext(ParsedArguments args);

    /**
     * Checks whether the argument is available in the given context.
     * @param ctx  the CLI context
     * @return  true if the argument is available in the given CLI context, false otherwise.
     */
    boolean isAvailable(CommandContext ctx);

    /**
     * Returns the value of the argument specified on the command line.
     * If the argument isn't specified the returned value is null.
     * Although, it might throw IllegalArgumentException in case the argument is a required one.
     * @param args  parsed arguments.
     * @return  the value of the argument or null if the argument isn't present or is missing value.
     */
    String getValue(ParsedArguments args);

    /**
     * Checks whether the argument accepts value.
     * @return  true if this argument accepts a value, otherwise false.
     */
    boolean isValueRequired();

    /**
     * Returns the tab-completer for the value.
     * @return  tab-completer for the value or null of none available.
     */
    CommandLineCompleter getValueCompleter();
}
