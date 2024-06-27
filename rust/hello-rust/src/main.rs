// https://www.rust-lang.org/learn/get-started

use ferris_says::say;
use std::io::{stdout, BufWriter};

fn hi() -> String {
    String::from("Hello fellow Rustaceans!")
}

fn main() {
   let stdout = stdout();
   let message = hi();
   let width = message.chars().count();

   let mut writer = BufWriter::new(stdout.lock());
   say(&message, width, &mut writer).unwrap();
}
